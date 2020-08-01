import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './adjust-client.reducer';
import { IAdjustClient } from 'app/shared/model/adjust-client.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdjustClientDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AdjustClientDetail = (props: IAdjustClientDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { adjustClientEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.adjustClient.detail.title">AdjustClient</Translate> [<b>{adjustClientEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="username">
              <Translate contentKey="adjustApp.adjustClient.username">Username</Translate>
            </span>
          </dt>
          <dd>{adjustClientEntity.username}</dd>
          <dt>
            <span id="firstName">
              <Translate contentKey="adjustApp.adjustClient.firstName">First Name</Translate>
            </span>
          </dt>
          <dd>{adjustClientEntity.firstName}</dd>
          <dt>
            <span id="lastName">
              <Translate contentKey="adjustApp.adjustClient.lastName">Last Name</Translate>
            </span>
          </dt>
          <dd>{adjustClientEntity.lastName}</dd>
          <dt>
            <span id="birthDate">
              <Translate contentKey="adjustApp.adjustClient.birthDate">Birth Date</Translate>
            </span>
          </dt>
          <dd>
            {adjustClientEntity.birthDate ? (
              <TextFormat value={adjustClientEntity.birthDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="age">
              <Translate contentKey="adjustApp.adjustClient.age">Age</Translate>
            </span>
          </dt>
          <dd>{adjustClientEntity.age}</dd>
          <dt>
            <span id="gender">
              <Translate contentKey="adjustApp.adjustClient.gender">Gender</Translate>
            </span>
          </dt>
          <dd>{adjustClientEntity.gender}</dd>
          <dt>
            <span id="token">
              <Translate contentKey="adjustApp.adjustClient.token">Token</Translate>
            </span>
          </dt>
          <dd>{adjustClientEntity.token}</dd>
          <dt>
            <span id="score">
              <Translate contentKey="adjustApp.adjustClient.score">Score</Translate>
            </span>
          </dt>
          <dd>{adjustClientEntity.score}</dd>
          <dt>
            <span id="image">
              <Translate contentKey="adjustApp.adjustClient.image">Image</Translate>
            </span>
          </dt>
          <dd>
            {adjustClientEntity.image ? (
              <div>
                {adjustClientEntity.imageContentType ? (
                  <a onClick={openFile(adjustClientEntity.imageContentType, adjustClientEntity.image)}>
                    <img
                      src={`data:${adjustClientEntity.imageContentType};base64,${adjustClientEntity.image}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {adjustClientEntity.imageContentType}, {byteSize(adjustClientEntity.image)}
                </span>
              </div>
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/adjust-client" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/adjust-client/${adjustClientEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ adjustClient }: IRootState) => ({
  adjustClientEntity: adjustClient.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustClientDetail);
