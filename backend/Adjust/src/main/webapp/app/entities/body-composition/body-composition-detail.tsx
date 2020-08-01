import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './body-composition.reducer';
import { IBodyComposition } from 'app/shared/model/body-composition.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBodyCompositionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BodyCompositionDetail = (props: IBodyCompositionDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { bodyCompositionEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.bodyComposition.detail.title">BodyComposition</Translate> [<b>{bodyCompositionEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="createdAt">
              <Translate contentKey="adjustApp.bodyComposition.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {bodyCompositionEntity.createdAt ? (
              <TextFormat value={bodyCompositionEntity.createdAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="height">
              <Translate contentKey="adjustApp.bodyComposition.height">Height</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.height}</dd>
          <dt>
            <span id="weight">
              <Translate contentKey="adjustApp.bodyComposition.weight">Weight</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.weight}</dd>
          <dt>
            <span id="bmi">
              <Translate contentKey="adjustApp.bodyComposition.bmi">Bmi</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.bmi}</dd>
          <dt>
            <span id="bodyCompositionFile">
              <Translate contentKey="adjustApp.bodyComposition.bodyCompositionFile">Body Composition File</Translate>
            </span>
          </dt>
          <dd>
            {bodyCompositionEntity.bodyCompositionFile ? (
              <div>
                {bodyCompositionEntity.bodyCompositionFileContentType ? (
                  <a onClick={openFile(bodyCompositionEntity.bodyCompositionFileContentType, bodyCompositionEntity.bodyCompositionFile)}>
                    <img
                      src={`data:${bodyCompositionEntity.bodyCompositionFileContentType};base64,${bodyCompositionEntity.bodyCompositionFile}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {bodyCompositionEntity.bodyCompositionFileContentType}, {byteSize(bodyCompositionEntity.bodyCompositionFile)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="bloodTestFile">
              <Translate contentKey="adjustApp.bodyComposition.bloodTestFile">Blood Test File</Translate>
            </span>
          </dt>
          <dd>
            {bodyCompositionEntity.bloodTestFile ? (
              <div>
                {bodyCompositionEntity.bloodTestFileContentType ? (
                  <a onClick={openFile(bodyCompositionEntity.bloodTestFileContentType, bodyCompositionEntity.bloodTestFile)}>
                    <img
                      src={`data:${bodyCompositionEntity.bloodTestFileContentType};base64,${bodyCompositionEntity.bloodTestFile}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {bodyCompositionEntity.bloodTestFileContentType}, {byteSize(bodyCompositionEntity.bloodTestFile)}
                </span>
              </div>
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/body-composition" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/body-composition/${bodyCompositionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ bodyComposition }: IRootState) => ({
  bodyCompositionEntity: bodyComposition.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BodyCompositionDetail);
