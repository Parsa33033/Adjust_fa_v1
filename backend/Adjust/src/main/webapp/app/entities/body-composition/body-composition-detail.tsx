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
            <span id="wrist">
              <Translate contentKey="adjustApp.bodyComposition.wrist">Wrist</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.wrist}</dd>
          <dt>
            <span id="waist">
              <Translate contentKey="adjustApp.bodyComposition.waist">Waist</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.waist}</dd>
          <dt>
            <span id="lbm">
              <Translate contentKey="adjustApp.bodyComposition.lbm">Lbm</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.lbm}</dd>
          <dt>
            <span id="muscleMass">
              <Translate contentKey="adjustApp.bodyComposition.muscleMass">Muscle Mass</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.muscleMass}</dd>
          <dt>
            <span id="muscleMassPercentage">
              <Translate contentKey="adjustApp.bodyComposition.muscleMassPercentage">Muscle Mass Percentage</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.muscleMassPercentage}</dd>
          <dt>
            <span id="fatMass">
              <Translate contentKey="adjustApp.bodyComposition.fatMass">Fat Mass</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.fatMass}</dd>
          <dt>
            <span id="fatMassPercentage">
              <Translate contentKey="adjustApp.bodyComposition.fatMassPercentage">Fat Mass Percentage</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.fatMassPercentage}</dd>
          <dt>
            <span id="gender">
              <Translate contentKey="adjustApp.bodyComposition.gender">Gender</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.gender}</dd>
          <dt>
            <span id="age">
              <Translate contentKey="adjustApp.bodyComposition.age">Age</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.age}</dd>
          <dt>
            <span id="bodyImage">
              <Translate contentKey="adjustApp.bodyComposition.bodyImage">Body Image</Translate>
            </span>
          </dt>
          <dd>
            {bodyCompositionEntity.bodyImage ? (
              <div>
                {bodyCompositionEntity.bodyImageContentType ? (
                  <a onClick={openFile(bodyCompositionEntity.bodyImageContentType, bodyCompositionEntity.bodyImage)}>
                    <img
                      src={`data:${bodyCompositionEntity.bodyImageContentType};base64,${bodyCompositionEntity.bodyImage}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {bodyCompositionEntity.bodyImageContentType}, {byteSize(bodyCompositionEntity.bodyImage)}
                </span>
              </div>
            ) : null}
          </dd>
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
          <dt>
            <Translate contentKey="adjustApp.bodyComposition.program">Program</Translate>
          </dt>
          <dd>{bodyCompositionEntity.programId ? bodyCompositionEntity.programId : ''}</dd>
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
