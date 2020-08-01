import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './specialist.reducer';
import { ISpecialist } from 'app/shared/model/specialist.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISpecialistDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SpecialistDetail = (props: ISpecialistDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { specialistEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.specialist.detail.title">Specialist</Translate> [<b>{specialistEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="username">
              <Translate contentKey="adjustApp.specialist.username">Username</Translate>
            </span>
          </dt>
          <dd>{specialistEntity.username}</dd>
          <dt>
            <span id="firstName">
              <Translate contentKey="adjustApp.specialist.firstName">First Name</Translate>
            </span>
          </dt>
          <dd>{specialistEntity.firstName}</dd>
          <dt>
            <span id="lastName">
              <Translate contentKey="adjustApp.specialist.lastName">Last Name</Translate>
            </span>
          </dt>
          <dd>{specialistEntity.lastName}</dd>
          <dt>
            <span id="birth">
              <Translate contentKey="adjustApp.specialist.birth">Birth</Translate>
            </span>
          </dt>
          <dd>
            {specialistEntity.birth ? <TextFormat value={specialistEntity.birth} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="gender">
              <Translate contentKey="adjustApp.specialist.gender">Gender</Translate>
            </span>
          </dt>
          <dd>{specialistEntity.gender}</dd>
          <dt>
            <span id="degree">
              <Translate contentKey="adjustApp.specialist.degree">Degree</Translate>
            </span>
          </dt>
          <dd>{specialistEntity.degree}</dd>
          <dt>
            <span id="field">
              <Translate contentKey="adjustApp.specialist.field">Field</Translate>
            </span>
          </dt>
          <dd>{specialistEntity.field}</dd>
          <dt>
            <span id="resume">
              <Translate contentKey="adjustApp.specialist.resume">Resume</Translate>
            </span>
          </dt>
          <dd>{specialistEntity.resume}</dd>
          <dt>
            <span id="stars">
              <Translate contentKey="adjustApp.specialist.stars">Stars</Translate>
            </span>
          </dt>
          <dd>{specialistEntity.stars}</dd>
          <dt>
            <span id="image">
              <Translate contentKey="adjustApp.specialist.image">Image</Translate>
            </span>
          </dt>
          <dd>
            {specialistEntity.image ? (
              <div>
                {specialistEntity.imageContentType ? (
                  <a onClick={openFile(specialistEntity.imageContentType, specialistEntity.image)}>
                    <img src={`data:${specialistEntity.imageContentType};base64,${specialistEntity.image}`} style={{ maxHeight: '30px' }} />
                  </a>
                ) : null}
                <span>
                  {specialistEntity.imageContentType}, {byteSize(specialistEntity.image)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="busy">
              <Translate contentKey="adjustApp.specialist.busy">Busy</Translate>
            </span>
          </dt>
          <dd>{specialistEntity.busy ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/specialist" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/specialist/${specialistEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ specialist }: IRootState) => ({
  specialistEntity: specialist.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SpecialistDetail);
